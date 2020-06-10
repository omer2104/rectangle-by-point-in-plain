package com.tranzmate.exercise;

import java.util.ArrayList;
import java.util.Collection;

public class Store implements IRectanglesStore {
    private ArrayList<IRectangle> _topLeftSorted;
    private ArrayList<IRectangle> _topRightSorted;
    private ArrayList<IRectangle> _bottomLeftSorted;
    private ArrayList<IRectangle> _bottomRightSorted;
    private IRectangle _container;

    @Override
    public void initialize(IRectangle bounds, Collection<IRectangle> rectangles) {
        _container = bounds;

        _topLeftSorted = new ArrayList<>(rectangles);
        _topLeftSorted.sort((r1, r2) -> {
            if (r1.getTop() == r2.getTop()) {
                return r2.getLeft() - r1.getLeft(); // [NOTICE] If r1 is "leftier" than r2, this value will be > 0
            }

            return r2.getTop() - r1.getTop(); // [NOTICE] If r1 is above r2, this value will be > 0
        });

        _topRightSorted = new ArrayList<>(rectangles);
        _topRightSorted.sort((r1, r2) -> {
            if (r1.getTop() == r2.getTop()) {
                return r1.getRight() - r2.getRight(); // [NOTICE] If r1 is "rightier" than r2, this value will be > 0
            }

            return r2.getTop() - r1.getTop(); // [NOTICE] If r1 is above r2, this value will be > 0
        });

        _bottomLeftSorted = new ArrayList<>(rectangles);
        _bottomLeftSorted.sort((r1, r2) -> {
            if (r1.getBottom() == r2.getBottom()) {
                return r2.getLeft() - r1.getLeft(); // [NOTICE] If r1 is "leftier" than r2, this value will be > 0
            }

            return r1.getBottom() - r2.getBottom(); // [NOTICE] If r1 is below r2, this value will be > 0
        });

        _bottomRightSorted = new ArrayList<>(rectangles);
        _bottomRightSorted.sort((r1, r2) -> {
            if (r1.getTop() == r2.getTop()) {
                return r1.getRight() - r2.getRight(); // [NOTICE] If r1 is "rightier" than r2, this value will be > 0
            }

            return r1.getBottom() - r2.getBottom(); // [NOTICE] If r1 is below r2, this value will be > 0
        });
    }

    @Override
    public IRectangle findRectangleAt(int x, int y) {
        if (!isPointInIRectangle(_container, x, y)) {
            return null;
        }

        IRectangle result = null;

        if (isPointInFirstQuadrant(x, y)) {
            for (int i = _topLeftSorted.size() - 1; i > -1; i--) {
                IRectangle rect = _topLeftSorted.get(i);

                if (topperLeftierTo(rect.getLeft(), rect.getTop(), x, y)) {
                    if (isPointInIRectangle(rect, x, y)) {
                        result = rect;
                        break; // This is the topmost rectangle because of the sorting
                    }
                } else {
                    break;
                }
            }
        } else if (isPointInSecondQuadrant(x, y)) {
            for (int i = _topRightSorted.size() - 1; i > -1; i--) {
                IRectangle rect = _topRightSorted.get(i);

                if (topperRightierTo(rect.getRight(), rect.getTop(), x, y)) {
                    if (isPointInIRectangle(rect, x, y)) {
                        result = rect;
                        break; // This is the topmost rectangle because of the sorting
                    }
                } else {
                    break;
                }
            }
        } else if (isPointInThirdQuadrant(x, y)) {
            for (int i = _bottomLeftSorted.size() - 1; i > -1; i--) {
                IRectangle rect = _bottomLeftSorted.get(i);

                if (bottomerLeftierTo(rect.getLeft(), rect.getBottom(), x, y)) {
                    if (isPointInIRectangle(rect, x, y)) {
                        if (result == null) {
                            result = rect;
                        } else {
                            if (rect.getTop() < result.getTop()) { // If rect is above result
                                result = rect;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        } else if (isPointInFourthQuadrant(x, y)) {
            for (int i = _bottomRightSorted.size() - 1; i > -1; i--) {
                IRectangle rect = _bottomRightSorted.get(i);

                if (bottomerLeftierTo(rect.getRight(), rect.getBottom(), x, y)) {
                    if (isPointInIRectangle(rect, x, y)) {
                        if (result == null) {
                            result = rect;
                        } else {
                            if (rect.getTop() < result.getTop()) { // If rect is above result
                                result = rect;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return result;
    }

    private static boolean isPointInIRectangle(IRectangle rect, int x, int y) {
        return y >= rect.getTop() && y <= rect.getBottom() &&
                x >= rect.getLeft() && x <= rect.getRight();
    }

    /*
    _________________
    |       |       |
    |    1  |   2   |
    |-------|-------|
    |    3  |   4   |
    |       |       |
    _________________
    */
    private boolean isPointInFirstQuadrant(int x, int y) {
        return x <= getMiddleX() && y <= getMiddleY();
    }
    private boolean isPointInSecondQuadrant(int x, int y) {
        return x >= getMiddleX() && y <= getMiddleY();
    }
    private boolean isPointInThirdQuadrant(int x, int y) {
        return x <= getMiddleX() && y >= getMiddleY();
    }
    private boolean isPointInFourthQuadrant(int x, int y) {
        return x >= getMiddleX() && y >= getMiddleY();
    }

    private double getMiddleX() {
        return _container.getLeft() + (_container.getRight() - _container.getLeft()) / 2;
    }

    private double getMiddleY() {
        return _container.getTop() + (_container.getBottom() - _container.getTop()) /2;
    }

    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return if (x1, y1) is more top left than (x2, y2)
     */
    private boolean topperLeftierTo(int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            return x2 >= x1; // [NOTICE] If x2 is bigger than x1, than it is more to the left in the bounds
        }

        return y2 >= y1; // [NOTICE] If y2 is bigger than y1, than y1 is above y2
    }

    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return if (x1, y1) is more top right than (x2, y2)
     */
    private boolean topperRightierTo(int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            return x1 >= x2; // [NOTICE] If x1 is bigger than x2, than it is more to the right in the bounds
        }

        return y2 >= y1; // [NOTICE] If y2 is bigger than y1, than y1 is above y2
    }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return if (x1, y1) is more bottom left than (x2, y2)
     */
    private boolean bottomerLeftierTo(int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            return x2 >= x1; // [NOTICE] If x2 is bigger than x1, than it is more to the left in the bounds
        }

        return y1 >= y2; // [NOTICE] If y1 is bigger than y2, than it is y1 is below y2
    }

    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return if (x1, y1) is more bottom left than (x2, y2)
     */
    private boolean bottomerRightierTo(int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            return x1 >= x2; // [NOTICE] If x1 is bigger than x2, than it is more to the right in the bounds
        }

        return y1 >= y2; // [NOTICE] If y1 is bigger than y2, than it is y1 is below y2
    }
}