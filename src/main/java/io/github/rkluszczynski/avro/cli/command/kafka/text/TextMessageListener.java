package io.github.rkluszczynski.avro.cli.command.kafka.text;

import io.github.rkluszczynski.avro.cli.command.kafka.ExtendedMessageListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public class TextMessageListener extends ExtendedMessageListener<String, String> {
    public void onMessage(List<ConsumerRecord<String, String>> data) {
        data.forEach(this::onMessage);
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        log.info(String.format("{%d} offset == %d, partition == %d", incrementAndGet(), record.offset(), record.partition()));

        System.out.printf("%s%n%n", record.value());
    }

    private Log log = LogFactory.getLog(TextMessageListener.class);
}