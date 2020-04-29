package com.ruoyi.common.utils.file;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
/**
 * @program: ruoyi->CompressUtil
 * @author: gakki
 * @create: 2020-04-28 18:05
 **/
public class CompressUtil {

    /**
     * 解压
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] decompressPack(byte[] data) throws IOException {
        return decompressPack(data, false);
    }

    /**
     * 解压
     * If the parameter 'nowrap' is true then the ZLIB header and checksum fields will not be used
     * 解压分钟行情数据时送TRUE
     * @param data
     * @param nowrap
     * @return
     * @throws IOException
     */
    public static byte[] decompressPack(byte[] data, boolean nowrap) throws IOException {
        if (data != null && data.length > 0) {
            byte[] output = null;
            Inflater decompresser = new Inflater(nowrap);
            decompresser.reset();
            decompresser.setInput(data);
            ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
            try {
                byte[] buf = new byte[1024];
                while (!decompresser.finished()) {
                    int i = decompresser.inflate(buf);
                    o.write(buf, 0, i);
                }
                output = o.toByteArray();
            } catch (Exception e) {
                output = data;
            } finally {
                try {
                    o.close();
                } catch (Exception e) {
                }
            }
            decompresser.end();
            return output;

        }
        return null;
    }

    /**
     * 压缩
     *
     * @param data 待压缩数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compressPack(byte[] data) throws IOException {
        byte[] output = null;
        ByteArrayOutputStream bos = null;
        Deflater compresser = new Deflater();
        try {
            compresser.reset();
            compresser.setInput(data);
            compresser.finish();
            bos = new ByteArrayOutputStream(data.length);

            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
        } finally {
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
            }
        }
        compresser.end();
        return output;
    }
}
