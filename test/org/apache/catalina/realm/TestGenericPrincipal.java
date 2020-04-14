
package org.apache.catalina.realm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestGenericPrincipal {

    private static final String USER = "user";
    private static final String PASSWORD = "pwd";
    private static final List<String> ROLES = Collections.unmodifiableList(
            Arrays.asList(new String[] { "ROLE1", "ROLE2" }));
    private static final TesterPrincipal PRINCIPAL = new TesterPrincipal("Principal");
    private static final TesterPrincipalNonSerializable PRINCIPAL_NON_SERIALIZABLE =
            new TesterPrincipalNonSerializable("PrincipalNonSerializable");

    @Test
    public void testSerialize01() throws ClassNotFoundException, IOException {
        GenericPrincipal gpIn = new GenericPrincipal(USER, PASSWORD, ROLES);
        doTest(gpIn);
    }

    @Test
    public void testSerialize02() throws ClassNotFoundException, IOException {
        GenericPrincipal gpIn = new GenericPrincipal(USER, PASSWORD, ROLES, PRINCIPAL);
        doTest(gpIn);
    }

    @Test
    public void testSerialize03() throws ClassNotFoundException, IOException {
        GenericPrincipal gpIn = new GenericPrincipal(USER, PASSWORD, ROLES, PRINCIPAL_NON_SERIALIZABLE);
        doTest(gpIn);
    }

    private void doTest(GenericPrincipal gpIn)
            throws ClassNotFoundException, IOException {
        GenericPrincipal gpOut = serializeAndDeserialize(gpIn);

        Assert.assertNull(gpOut.getGssCredential());
        Assert.assertEquals(gpIn.getName(), gpOut.getName());
        Assert.assertEquals(gpIn.getPassword(), gpOut.getPassword());
        Assert.assertArrayEquals(gpIn.getRoles(), gpOut.getRoles());
        if (gpIn == gpIn.getUserPrincipal()) {
            Assert.assertEquals(gpOut, gpOut.getUserPrincipal());
        } else if (gpIn.getUserPrincipal() instanceof Serializable) {
            Assert.assertEquals(gpIn.getUserPrincipal(), gpOut.getUserPrincipal());
        } else {
            Assert.assertEquals(gpOut, gpOut.getUserPrincipal());
        }
    }

    private GenericPrincipal serializeAndDeserialize(GenericPrincipal gpIn)
            throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(gpIn);

        byte[] data = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (GenericPrincipal) ois.readObject();
    }
}
