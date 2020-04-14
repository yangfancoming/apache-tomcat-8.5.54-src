

package org.apache.catalina.tribes;

/**
 * The MembershipListener interface is used as a callback to the
 * membership service. It has two methods that will notify the listener
 * when a member has joined the group and when a member has disappeared (crashed)
 */
public interface MembershipListener {
    /**
     * A member was added to the group
     * @param member Member - the member that was added
     */
    public void memberAdded(Member member);

    /**
     * A member was removed from the group<br>
     * If the member left voluntarily, the Member.getCommand will contain the Member.SHUTDOWN_PAYLOAD data
     * @param member Member
     * @see Member#SHUTDOWN_PAYLOAD
     */
    public void memberDisappeared(Member member);

}