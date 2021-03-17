package com.qiancijun.application.core.observer.impl;

import com.qiancijun.application.core.observer.Observer;
import com.qiancijun.application.core.observer.Subject;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/17 19:30
 */

public class OperationBtnSubject implements Subject {

    private Observer observer;

    public OperationBtnSubject(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notify(String val) {

    }

    @Override
    public void addVal() {
        observer.update();
    }
}
