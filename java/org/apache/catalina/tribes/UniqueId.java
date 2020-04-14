
package org.apache.catalina.tribes;

import java.io.Serializable;

import org.apache.catalina.tribes.util.Arrays;

/**
 * <p>Title: Represents a globally unique Id</p>
 *
 * <p>Company: </p>
 *
 * @version 1.0
 */
public final class UniqueId implements Serializable{
    private static final long serialVersionUID = 1L;

    final byte[] id;

    public UniqueId() {
        this(null);
    }

    public UniqueId(byte[] id) {
        this.id = id;
    }

    public UniqueId(byte[] id, int offset, int length) {
        this.id = new byte[length];
        System.arraycopy(id,offset,this.id,0,length);
    }

    @Override
    public int hashCode() {
        if ( id == null ) return 0;
        return Arrays.hashCode(id);
    }

    @Override
    public boolean equals(Object other) {
        boolean result = (other instanceof UniqueId);
        if ( result ) {
            UniqueId uid = (UniqueId)other;
            if ( this.id == null && uid.id == null ) result = true;
            else if ( this.id == null && uid.id != null ) result = false;
            else if ( this.id != null && uid.id == null ) result = false;
            else result = Arrays.equals(this.id,uid.id);
        }//end if
        return result;
    }

    public byte[] getBytes() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("UniqueId");
        buf.append(Arrays.toString(id));
        return buf.toString();
    }

}