package com.gvip.snrb.sdk.callbacks;

import java.util.ArrayList;

/**
 * Created by Patrick on 1/25/2018.
 */

public interface ICallbackEvent<T, E> {
    void onSuccess(T results);
    void onError(E error);
}
