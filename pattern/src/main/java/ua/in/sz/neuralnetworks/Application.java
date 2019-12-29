package ua.in.sz.neuralnetworks;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Neuron i1 = () -> 1.0f;
		Neuron i2 = () -> 2.0f;
		Neuron i3 = () -> 4.0f;

		Neuron h1 = HiddenNeuron.builder().neurons(Lists.newArrayList(i1, i2, i3)).build();

		Neuron o1 = h1::get;

		log.info("Result: {}", o1.get());
	}
}
