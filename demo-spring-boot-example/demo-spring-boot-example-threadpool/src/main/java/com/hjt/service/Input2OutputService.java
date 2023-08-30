package com.hjt.service;

import com.hjt.domain.Input;
import com.hjt.domain.Output;

import java.util.List;
import java.util.concurrent.Future;

public interface Input2OutputService {
    Output singleProcess(Input input);
    List<Output> multiProcess(List<Input> inputList);
    Future<Output> asyncProcess(Input input);
}
