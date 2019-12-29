package ua.in.sz.neuralnetworks;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Neuron i1 = () -> 1.0f;
		Neuron i2 = () -> 2.0f;
		Neuron i3 = () -> 4.0f;

		Neuron h1 = HiddenNeuron.builder().synapses(
				Lists.newArrayList(
						Synapse.builder().weight(1.0f).source(i1).build(),
						Synapse.builder().weight(1.1f).source(i2).build(),
						Synapse.builder().weight(1.0f).source(i3).build()
				)
		).build();

		Neuron o1 = HiddenNeuron.builder().synapses(
				Lists.newArrayList(
						Synapse.builder().weight(1.0f).source(h1).build()
				)
		).build();

		log.info("Result: {}", o1.get());
	}
}
