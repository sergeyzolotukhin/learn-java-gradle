package ua.in.sz.neuralnetworks;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
public class HiddenNeuron implements Neuron {
	@Builder.Default
	private List<Synapse> synapses = new ArrayList<>();

	@Override
	public Float get() {
		float result = 0;

		for (Synapse synapse : synapses) {
			result += synapse.get();
		}

		return result;
	}
}
