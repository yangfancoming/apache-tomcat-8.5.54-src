
package org.apache.tomcat.util.codec;

/**
 * Defines common decoding methods for byte array decoders.
 *
 * @deprecated This interface is unused and will be removed in Tomcat 9
 */
@Deprecated
public interface BinaryDecoder extends Decoder {

    /**
     * Decodes a byte array and returns the results as a byte array.
     *
     * @param source
     *            A byte array which has been encoded with the appropriate encoder
     * @return a byte array that contains decoded content
     * @throws DecoderException
     *             A decoder exception is thrown if a Decoder encounters a failure condition during the decode process.
     */
    byte[] decode(byte[] source) throws DecoderException;
}

