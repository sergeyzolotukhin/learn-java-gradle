package ua.in.sz.neuralnetworks;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
public class HiddenNeuron implements Neuron {
	@Builder.Default
	private List<Neuron> neurons = new ArrayList<>();

	@Override
	public Float get() {
		float result = 0;

		for (Neuron neuron : neurons) {
			result += neuron.get();
		}

		return result;
	}
}
