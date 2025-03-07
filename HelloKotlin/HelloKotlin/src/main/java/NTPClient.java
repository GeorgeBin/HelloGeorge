import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class NTPClient {

    private static final String NTP_SERVER = "pool.ntp.org";
    private static final int NTP_PORT = 123;
    private static final int NTP_PACKET_SIZE = 48;
    private static final long DIFF_1900_TO_1970 = 2208988800L;

    public static void main(String[] args) {
        try {
            // 创建一个字节数组来存放 NTP 请求包
            byte[] ntpData = new byte[NTP_PACKET_SIZE];
            ntpData[0] = 0x1B; // 设置 NTP 请求头

            // 获取 NTP 服务器的地址
            InetAddress address = InetAddress.getByName(NTP_SERVER);

            // 创建一个 UDP 套接字并发送 NTP 请求
            DatagramPacket request = new DatagramPacket(ntpData, ntpData.length, address, NTP_PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(request);

            // 接收 NTP 响应
            DatagramPacket response = new DatagramPacket(ntpData, ntpData.length);
            socket.receive(response);

            // 关闭套接字
            socket.close();

            // 解析返回的时间
            long time = getTime(ntpData);
            System.out.println("NTP 时间（毫秒）: " + time);
            System.out.println("本地时间（毫秒）: " + System.currentTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long getTime(byte[] ntpData) {
        // 将返回的字节转换为时间戳
        ByteBuffer buffer = ByteBuffer.wrap(ntpData);
        long secondsSince1900 = buffer.getInt(40) & 0xFFFFFFFFL;
        long fraction = buffer.getInt(44) & 0xFFFFFFFFL;

        long millis = ((secondsSince1900 - DIFF_1900_TO_1970) * 1000) + (fraction * 1000 / 0x100000000L);
        return millis;
    }
}
