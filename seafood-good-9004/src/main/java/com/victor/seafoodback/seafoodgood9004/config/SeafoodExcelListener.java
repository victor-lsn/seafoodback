package com.victor.seafoodback.seafoodgood9004.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.victor.seafoodback.seafoodgood9004.service.impl.SeafoodServiceImpl;
import com.victor.seafoodback.vo.SeafoodVoExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SeafoodExcelListener extends AnalysisEventListener<SeafoodVoExcel> {
    //集合大小达到5条就插入数据
    private static final int BATCH_COUNT = 5;

    private List<SeafoodVoExcel> list = new ArrayList<>();

    @Autowired
    private SeafoodServiceImpl seafoodService;

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param seafoodVoExcel  每次解析出并封装的实体类
     * @param analysisContext
     */
    @Override
    public void invoke(SeafoodVoExcel seafoodVoExcel, AnalysisContext analysisContext) {
        list.add(seafoodVoExcel);
        log.info("添加数据：" + seafoodVoExcel);
        if (list.size() >= BATCH_COUNT) {
            //存储数据
            seafoodService.batchAddSeafood(list);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //存储数据，因为最后的数据不一定超过BATCH_COUNT
        seafoodService.batchAddSeafood(list);
        list.clear();
        log.info("批量数据插入完成");
    }
}
