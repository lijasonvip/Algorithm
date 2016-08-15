package com.bo.designpattern;

public enum AnimalHelperSingleton {
	INSTANCE {

        @Override
        protected void read() {
            System.out.println("read");
        }

        @Override
        protected void write() {
            System.out.println("write");
        }

    };
    protected abstract void read();
    protected abstract void write();
}
