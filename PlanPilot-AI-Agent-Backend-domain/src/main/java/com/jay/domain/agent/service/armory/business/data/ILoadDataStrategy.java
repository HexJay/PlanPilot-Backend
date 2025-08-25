package com.jay.domain.agent.service.armory.business.data;

import com.jay.domain.agent.model.entity.ArmoryCommandEntity;
import com.jay.domain.agent.service.armory.factory.DefaultArmoryStrategyFactory;

/**
 * @author Jay
 * @date 2025/8/24 15:29
 * @description
 */
public interface ILoadDataStrategy {

    void loadData(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext);

}
