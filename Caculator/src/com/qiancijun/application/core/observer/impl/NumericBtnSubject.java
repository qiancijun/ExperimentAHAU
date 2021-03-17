package com.qiancijun.application.core.observer.impl;

import com.qiancijun.application.core.observer.Observer;
import com.qiancijun.application.core.observer.Subject;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/17 9:12
 */

public class NumericBtnSubject implements Subject {

    private Observer observer;

    public NumericBtnSubject(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notify(String val) {
        observer.change(val);
    }

    @Override
    public void addVal() {

    }
}
