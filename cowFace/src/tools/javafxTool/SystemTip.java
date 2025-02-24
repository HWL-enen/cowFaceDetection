import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;

public class SystemTip {
    private String msgBody,msgHead;
    private TrayIcon.MessageType type;

    public SystemTip(String msgBody, String msgHead, TrayIcon.MessageType type) {
        this.msgBody = msgBody;
        this.msgHead = msgHead;
        this.type = type;
    }



    public void pushMsg() {
        // 检查系统是否支持系统托盘
        if (!SystemTray.isSupported()) {
            System.out.println("系统不支持系统托盘");
            return;
        }

        // 获取系统托盘实例
        final SystemTray tray = SystemTray.getSystemTray();

        // 加载图标
        URL imageUrl = SystemTip.class.getResource("res/images/login/cow.png");
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();

        // 创建弹出菜单
        PopupMenu popup = new PopupMenu();
        MenuItem defaultItem = new MenuItem("退出");
        defaultItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(tray.getTrayIcons()[0]);
                System.exit(0);
            }
        });
        popup.add(defaultItem);

        // 创建托盘图标
        final TrayIcon trayIcon = new TrayIcon(image, "系统通知", popup);
        trayIcon.setImageAutoSize(true);

        /*// 为托盘图标添加点击事件监听器
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "你点击了系统托盘图标！");
            }
        });*/

        try {
            // 将托盘图标添加到系统托盘中
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("无法将托盘图标添加到系统托盘中：" + e);
            return;
        }

        // 显示桌面通知
        trayIcon.displayMessage(msgHead, msgBody, type);
    }

}
