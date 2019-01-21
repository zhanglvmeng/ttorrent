package com.turn.ttorrent.tracker;

import com.turn.ttorrent.common.TorrentMetadata;
import com.turn.ttorrent.common.TorrentParser;

import java.io.File;
import java.io.IOException;

public class TrackerUtils {

  public static final String TEST_RESOURCES = "src/test/resources";

  public static TrackedTorrent loadTorrent(String name) throws IOException {
    // 获取元数据
    TorrentMetadata torrentMetadata = new TorrentParser().parseFromFile(new File(TEST_RESOURCES + "/torrents", name));
    // 利用元数据的hash定义 一个新的 tracked torrent
    return new TrackedTorrent(torrentMetadata.getInfoHash());
  }

}
