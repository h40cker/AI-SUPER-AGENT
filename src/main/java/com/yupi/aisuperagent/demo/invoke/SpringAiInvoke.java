package com.yupi.aisuperagent.demo.invoke;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringAiInvoke implements CommandLineRunner {

    @Resource
    private ChatModel ChatModel;
    @Autowired
    private DashScopeChatModel dashscopeChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage assistantMessage = dashscopeChatModel.call(new Prompt("你是谁"))
                        .getResult()
                                .getOutput();
        System.out.println(assistantMessage.getText());
    }
}
