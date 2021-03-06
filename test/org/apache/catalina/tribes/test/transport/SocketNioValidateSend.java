
package org.apache.catalina.tribes.test.transport;

import java.math.BigDecimal;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.membership.MemberImpl;
import org.apache.catalina.tribes.transport.nio.NioSender;

public class SocketNioValidateSend {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        Member mbr = new MemberImpl("localhost", 9999, 0);
        byte seq = 0;
        byte[] buf = new byte[50000];
        Arrays.fill(buf,seq);
        int len = buf.length;
        BigDecimal total = new BigDecimal((double)0);
        BigDecimal bytes = new BigDecimal((double)len);
        NioSender sender = new NioSender();
        sender.setDestination(mbr);
        sender.setDirectBuffer(true);
        sender.setSelector(selector);
        sender.connect();
        sender.setMessage(buf);
        System.out.println("Writing to 9999");
        long start = 0;
        double mb = 0;
        boolean first = true;
        int count = 0;

        DecimalFormat df = new DecimalFormat("##.00");
        while (count<100000) {
            if (first) {
                first = false;
                start = System.currentTimeMillis();
            }
            sender.setMessage(buf);
            int selectedKeys = 0;
            try {
                selectedKeys = selector.select(0);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            if (selectedKeys == 0) {
                continue;
            }

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                it.remove();
                try {
                    int readyOps = sk.readyOps();
                    sk.interestOps(sk.interestOps() & ~readyOps);
                    if (sender.process(sk, false)) {
                        total = total.add(bytes);
                        sender.reset();
                        seq++;
                        Arrays.fill(buf,seq);
                        sender.setMessage(buf);
                        mb += ( (double) len) / 1024 / 1024;
                        if ( ( (++count) % 10000) == 0) {
                            long time = System.currentTimeMillis();
                            double seconds = ( (double) (time - start)) / 1000;
                            System.out.println("Throughput " + df.format(mb / seconds) + " MB/seconds, total "+mb+" MB, total "+total+" bytes.");
                        }
                    }

                } catch (Throwable t) {
                    t.printStackTrace();
                    return;
                }
            }
        }
        System.out.println("Complete, sleeping 15 seconds");
        Thread.sleep(15000);
    }
}
