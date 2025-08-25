package com.jay.domain.agent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jay
 * @date 2025/8/24 15:13
 * @description 装配命令
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArmoryCommandEntity {
    /**
     * 命令类型
     */
    private String commandType;

    /**
     * 命令索引（clientId、modelId、apiId...）
     */
    private List<String> commandIdList;

}
