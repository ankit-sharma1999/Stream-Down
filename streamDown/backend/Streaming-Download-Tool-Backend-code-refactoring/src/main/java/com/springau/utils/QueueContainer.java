package com.springau.utils;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.stereotype.Component;


@Component
public class QueueContainer {

  private BlockingQueue<List<List<String>>> queue = new LinkedBlockingQueue<>();

  public BlockingQueue<List<List<String>>> getQueue() {
    return queue;
  }

  public void setQueue(BlockingQueue<List<List<String>>> queue) {
    this.queue = queue;
  }

}
