package org.ruslan.todo.mc.todo;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class KafkaStreamsApplicationTest {

    private final static String TEST_CONFIG_FILE = "./config/test.properties";
    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsApplicationTest.class);

    private static Topology buildTopology(String inputTopic, String outputTopic) {
        Serde<String> stringSerde = Serdes.String();

        StreamsBuilder builder = new StreamsBuilder();

        builder
                .stream(inputTopic, Consumed.with(stringSerde, stringSerde))
                .peek((k,v) -> logger.info("Observed event: {}", v))
                .mapValues((ValueMapper<String, String>) String::toUpperCase)
                .peek((k,v) -> logger.info("Transformed event: {}", v))
                .to(outputTopic, Produced.with(stringSerde, stringSerde));

        return builder.build();
    }

    @Test
    public void topologyShouldUpperCaseInputs() throws IOException {

        final Properties props = new Properties();
        try (InputStream inputStream = new FileInputStream(TEST_CONFIG_FILE)) {
            props.load(inputStream);
        }

        final String inputTopicName = props.getProperty("input.topic.name");
        final String outputTopicName = props.getProperty("output.topic.name");

        final Topology topology = KafkaStreamsApplicationTest.buildTopology(inputTopicName, outputTopicName);

        try (final TopologyTestDriver testDriver = new TopologyTestDriver(topology, props)) {
            Serde<String> stringSerde = Serdes.String();

            final TestInputTopic<String, String> inputTopic = testDriver
                    .createInputTopic(inputTopicName, stringSerde.serializer(), stringSerde.serializer());
            final TestOutputTopic<String, String> outputTopic = testDriver
                    .createOutputTopic(outputTopicName, stringSerde.deserializer(), stringSerde.deserializer());

            List<String> inputs = Arrays.asList(
                    "Chuck Norris can write multi-threaded applications with a single thread.",
                    "No statement can catch the ChuckNorrisException.",
                    "Chuck Norris can binary search unsorted data."
            );
            List<String> expectedOutputs = inputs.stream()
                    .map(String::toUpperCase).collect(Collectors.toList());

            inputs.forEach(inputTopic::pipeInput);
            final List<String> actualOutputs = outputTopic.readValuesToList();

            assertThat(expectedOutputs, equalTo(actualOutputs));

        }

    }
}