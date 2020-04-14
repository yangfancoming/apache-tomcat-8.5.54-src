
package org.apache.catalina.ant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tools.ant.BuildException;

public class TestDeployTask extends TomcatBaseTest {

    @Test
    public void bug58086a() {
        DeployTask deployTask = new DeployTask() {

            @Override
            public void execute(String command, InputStream istream, String contentType, long contentLength)
                    throws BuildException {
                Assert.assertEquals("/deploy?path=somepath", command);
                Assert.assertEquals("application/octet-stream", contentType);
                try {
                    istream.close();
                } catch (IOException e) {
                }
            }

        };

        setDefaults(deployTask);

        testExecute(deployTask, "file:./test/deployment/context.war");
        testExecute(deployTask, new File("test/deployment/context.war").toURI().toString());
        testExecute(deployTask, new File("test/deployment/context.war").getAbsolutePath());
        testExecute(deployTask, "jar:" + new File("test/deployment/context.jar").toURI().toString() + "!/context.war");
        testExecute(deployTask, "file:./test/deployment/dir with spaces/context.war");
        testExecute(deployTask, new File("test/deployment/dir with spaces/context.war").toURI().toString());
        testExecute(deployTask, new File("test/deployment/dir with spaces/context.war").getAbsolutePath());
        testExecute(deployTask, "jar:" + new File("test/deployment/dir with spaces/context.jar").toURI().toString()
                + "!/context.war");
        testExecute(deployTask, "file:./test/deployment/dir%20with%20spaces/context.war");
    }

    @Test(expected = BuildException.class)
    public void bug58086b() {
        DeployTask deployTask = new DeployTask();
        setDefaults(deployTask);
        testExecute(deployTask, "scheme:./test/deployment/context.war");
    }

    @Test(expected = BuildException.class)
    public void bug58086c() {
        DeployTask deployTask = new DeployTask();
        setDefaults(deployTask);
        testExecute(deployTask, "sc:./test/deployment/context.war");
    }

    @Test
    public void bug58086d() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File root = new File("test/deployment");
        tomcat.addWebapp("", root.getAbsolutePath());

        tomcat.start();

        DeployTask deployTask = new DeployTask() {

            @Override
            public void execute(String command, InputStream istream, String contentType, long contentLength)
                    throws BuildException {
                Assert.assertEquals("/deploy?path=somepath", command);
                Assert.assertEquals("application/octet-stream", contentType);
                try {
                    istream.close();
                } catch (IOException e) {
                }
            }

        };

        setDefaults(deployTask);

        testExecute(deployTask, "http://localhost:" + getPort() + "/context.war");
    }

    private void setDefaults(DeployTask deployTask) {
        deployTask.setUrl("someurl");
        deployTask.setUsername("someuser");
        deployTask.setPassword("somepassword");
        deployTask.setPath("somepath");
    }

    private void testExecute(DeployTask deployTask, String war) {
        deployTask.setWar(war);
        deployTask.execute();
    }
}
