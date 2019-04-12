package com.zph.cins.base.frag;

import com.zph.cins.base.view.BaseView;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author zph
 * @date 2018/3/22
 */

public interface BaseMainView extends BaseView {
    void onLoadAllFragMentViewFinish(List<HashMap<String, String>> categoryList);
}
