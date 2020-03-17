package com.hevan.library.base;


import com.hevan.library.data.ITasksDataSource;

/**
 * Created by huangwx on 2016/6/15.
 */
public interface IBasePresenter {

    public void start();

    public ITasksDataSource getTasksDataSource();
}
