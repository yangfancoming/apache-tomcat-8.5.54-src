
package org.apache.catalina.tribes.group.interceptors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.TesterUtil;
import org.apache.catalina.tribes.group.GroupChannel;

public class TestNonBlockingCoordinator {

    private static final int CHANNEL_COUNT = 10;

    private GroupChannel[] channels = null;
    private NonBlockingCoordinator[] coordinators = null;

    @Before
    public void setUp() throws Exception {
        System.out.println("Setup");
        channels = new GroupChannel[CHANNEL_COUNT];
        coordinators = new NonBlockingCoordinator[CHANNEL_COUNT];
        Thread[] threads = new Thread[CHANNEL_COUNT];
        for ( int i=0; i<CHANNEL_COUNT; i++ ) {
            channels[i] = new GroupChannel();
            coordinators[i] = new NonBlockingCoordinator();
            channels[i].addInterceptor(coordinators[i]);
            channels[i].addInterceptor(new TcpFailureDetector());
            final int j = i;
            threads[i] = new Thread() {
                @Override
                public void run() {
                    try {
                        channels[j].start(Channel.DEFAULT);
                        Thread.sleep(50);
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            };
        }
        TesterUtil.addRandomDomain(channels);
        for (int i = 0; i < CHANNEL_COUNT; i++) {
            threads[i].start();
        }
        for (int i = 0; i < CHANNEL_COUNT; i++) {
            threads[i].join();
        }
        Thread.sleep(1000);
    }

    @Test
    public void testCoord1() throws Exception {
        int expectedCount = channels[0].getMembers().length;
        for (int i = 1; i < CHANNEL_COUNT; i++) {
            Assert.assertEquals("Message count expected to be equal.", expectedCount,
                    channels[i].getMembers().length);
        }
        Member member = coordinators[0].getCoordinator();
        int cnt = 0;
        while (member == null && (cnt++ < 100)) {
            try {
                Thread.sleep(100);
                member = coordinators[0].getCoordinator();
            } catch (Exception x) {
                /* Ignore */
            }
        }
        for (int i = 0; i < CHANNEL_COUNT; i++) {
            Assert.assertEquals(member, coordinators[i].getCoordinator());
        }
        System.out.println("Coordinator[1] is:" + member);
    }

    @Test
    public void testCoord2() throws Exception {
        Member member = coordinators[1].getCoordinator();
        System.out.println("Coordinator[2a] is:" + member);
        int index = -1;
        for ( int i=0; i<CHANNEL_COUNT; i++ ) {
            if ( channels[i].getLocalMember(false).equals(member) ) {
                System.out.println("Shutting down:" + channels[i].getLocalMember(true).toString());
                channels[i].stop(Channel.DEFAULT);
                index = i;
            }
        }
        int dead = index;
        Thread.sleep(1000);
        if (index == 0) {
            index = 1;
        } else {
            index = 0;
        }
        System.out.println("Member count:"+channels[index].getMembers().length);
        member = coordinators[index].getCoordinator();
        for (int i = 1; i < CHANNEL_COUNT; i++) {
            if (i != dead) {
                Assert.assertEquals(member, coordinators[i].getCoordinator());
            }
        }
        System.out.println("Coordinator[2b] is:" + member);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
        for ( int i=0; i<CHANNEL_COUNT; i++ ) {
            channels[i].stop(Channel.DEFAULT);
        }
    }

}
