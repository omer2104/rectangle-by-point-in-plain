package com.tranzmate.exercise;

import java.util.Properties;

public class Rectangle implements IRectangle {
    private int _top, _bottom, _left, _right;

    public Rectangle(int top, int bottom, int left, int right) {
        this._top = top;
        this._bottom = bottom;
        this._left = left;
        this._right = right;
    }

    @Override
    public int getLeft() {
        return this._left;
    }

    @Override
    public int getTop() {
        return this._top;
    }

    @Override
    public int getRight() {
        return this._right;
    }

    @Override
    public int getBottom() {
        return this._bottom;
    }

    @Override
    public Properties getProperties() {
        return new Properties();
    }

    @Override
    public String toString() {
        return "top: " + _top + " bottom: " + _bottom + " left: " + _left + " right: " + _right;
    }
}