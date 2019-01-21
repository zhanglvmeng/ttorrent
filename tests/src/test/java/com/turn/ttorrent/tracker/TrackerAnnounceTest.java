package com.turn.ttorrent.tracker;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.turn.ttorrent.tracker.TrackerUtils.loadTorrent;
import static org.testng.Assert.assertEquals;

@Test
public class TrackerAnnounceTest {

  private Tracker tracker;

  @BeforeMethod
  public void setUp() throws Exception {
    this.tracker = new Tracker(6969);
    // 为tracker服务器 设置 announcIntervale 间隔， 还不知道干啥用。。。。
    tracker.setAnnounceInterval(5);
    // 设置torrent 超时时间， 用途待后面确定。
    tracker.setPeerCollectorExpireTimeout(10);
    // 启动tracker线程，里面包含了启动了一个tracker的server
    this.tracker.start(false);
  }

  public void test_announce() throws IOException {

    assertEquals(0, this.tracker.getTrackedTorrents().size());
    // 在tracker上 注册 torrent（种子）。 其中 loadTorrent 是加载种子文件。
    this.tracker.announce(loadTorrent("file1.jar.torrent"));

    assertEquals(1, this.tracker.getTrackedTorrents().size());
  }

  @AfterMethod
  public void tearDown() throws Exception {
    // 关闭tracker 连接。
    this.tracker.stop();
  }
}
