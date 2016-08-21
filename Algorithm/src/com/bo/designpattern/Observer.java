package com.bo.designpattern;

import java.awt.List;
import java.util.ArrayList;

public class Observer {
	public static void main(String[] args) {
		Topic girl = new TopicA();
		Watcher w1 = new WatcherA();
		Watcher w2 = new WatcherA();
		
		girl.addWatcher(w1);
		girl.addWatcher(w2);
		
		girl.notifyWatchers("Happpy");
	}
}

interface Watcher{
	public void update(String str);
}

class WatcherA implements Watcher{
	@Override
	public void update(String str) {
		System.out.println(str);
	}
}

interface Topic{
	public void addWatcher(Watcher w);
	public void removeWatcher(Watcher w);
	public void notifyWatchers(String str);
}

class TopicA implements Topic{
	private ArrayList<Watcher> list = new ArrayList<Watcher>();
	
	@Override
	public void addWatcher(Watcher w) {
		list.add(w);
	}
	
	@Override
	public void removeWatcher(Watcher w) {
		list.remove(w);
	}
	
	@Override
	public void notifyWatchers(String str) {
		for(Watcher w:list){
			w.update(str);
		}
	}
}
