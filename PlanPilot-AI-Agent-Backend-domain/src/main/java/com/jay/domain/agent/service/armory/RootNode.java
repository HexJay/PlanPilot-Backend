package com.jay.domain.agent.service.armory;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson2.JSON;
import com.jay.domain.agent.model.entity.ArmoryCommandEntity;
import com.jay.domain.agent.model.vo.enums.AiAgentEnumVO;
import com.jay.domain.agent.service.armory.business.data.ILoadDataStrategy;
import com.jay.domain.agent.service.armory.factory.DefaultArmoryStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jay
 * @date 2025/8/24 15:25
 * @description
 */
@Slf4j
@Service
public class RootNode extends AbstractArmorySupport{

    private final Map<String, ILoadDataStrategy> loadDataStrategyMap;

    @Resource
    private AiClientApiNode aiClientApiNode;

    public RootNode(Map<String, ILoadDataStrategy> loadDataStrategyMap) {
        this.loadDataStrategyMap = loadDataStrategyMap;
    }

    @Override
    protected String doApply(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("Ai Agent 构建，数据加载节点 {}", JSON.toJSONString(requestParameter));
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> get(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return aiClientApiNode;
    }

    @Override
    protected void multiThread(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 获取命令；不同的命令类型，对应不同的数据加载策略
        String commandType = requestParameter.getCommandType();

        // 获取策略
        AiAgentEnumVO aiAgentEnumVO = AiAgentEnumVO.getByCode(commandType);
        String loadDataStrategyKey = aiAgentEnumVO.getLoadDataStrategy();

        // 加载数据
        ILoadDataStrategy loadDataStrategy = loadDataStrategyMap.get(loadDataStrategyKey);
        loadDataStrategy.loadData(requestParameter, dynamicContext);

    }
}
