/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mvp.cn.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import mvp.cn.common.MvpFragment;
import mvp.cn.common.MvpView;


public abstract class MvpRxFragment<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpFragment<V, P> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MvpRxPresenter p = getPresenter();
        if (p != null) {
            p.setModel(createModel());
        }
    }

    public abstract M createModel();
}