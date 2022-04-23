/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.impl.cmd;

import java.io.Serializable;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

/**
 *
 */
public class DeleteDeploymentCmd implements Command<Void>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String deploymentId;
    protected boolean cascade;

    public DeleteDeploymentCmd(String deploymentId, boolean cascade) {
        this.deploymentId = deploymentId;
        this.cascade = cascade;
    }

    public Void execute(CommandContext commandContext) {
        if (deploymentId == null) {
            throw new ActivitiIllegalArgumentException("deploymentId is null");
        }

        // Remove process definitions from cache:
        commandContext.getProcessEngineConfiguration().getDeploymentManager()
            .removeDeployment(deploymentId, cascade);

        return null;
    }
}
