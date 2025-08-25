package com.jay.test.domain;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.jay.domain.agent.model.entity.ArmoryCommandEntity;
import com.jay.domain.agent.model.vo.enums.AiAgentEnumVO;
import com.jay.domain.agent.service.armory.factory.DefaultArmoryStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jay
 * @date 2025/8/25 16:55
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AgentTest {
    @Resource
    private DefaultArmoryStrategyFactory defaultArmoryStrategyFactory;

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test_aiClientApiNode() throws Exception {
        StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> armoryStrategyHandler =
                defaultArmoryStrategyFactory.armoryStrategyHandler();

        String apply = armoryStrategyHandler.apply(
                ArmoryCommandEntity.builder()
                        .commandType(AiAgentEnumVO.AI_CLIENT.getCode())
                        .commandIdList(List.of("3001"))
                        .build(),
                new DefaultArmoryStrategyFactory.DynamicContext());

        OpenAiApi openAiApi = (OpenAiApi) applicationContext.getBean(AiAgentEnumVO.AI_CLIENT_API.getBeanName("1001"));

        log.info("测试结果：{}", openAiApi);
    }
}
