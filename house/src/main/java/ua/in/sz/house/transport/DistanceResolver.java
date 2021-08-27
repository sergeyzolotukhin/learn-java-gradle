package ua.in.sz.house.transport;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class DistanceResolver {
    private final Place housePlace;
    private final Place shopPlace;
    private final Place transportPlace;

    private static final Map<Pair<Place, Place>, Double> distances = ImmutableMap.<Pair<Place, Place>, Double>builder()
            .put(Pair.of(Place.MOROR_M, Place.TRAVITA), 26.8)
            .put(Pair.of(Place.TRAVITA, Place.VORZEL), 26.8)
            .put(Pair.of(Place.VORZEL, Place.MOROR_M), 33.6)
            .build();

    public DistanceResolver(Place housePlace, Place shopPlace, Place transportPlace) {
        this.housePlace = housePlace;
        this.shopPlace = shopPlace;
        this.transportPlace = transportPlace;
    }

    public double comeIn() {
        return distances.getOrDefault(Pair.of(transportPlace, shopPlace), 0.0);
    }

    public double travel() {
        return distances.getOrDefault(Pair.of(shopPlace, housePlace), 0.0);
    }

    public double comeOut() {
        return distances.getOrDefault(Pair.of(housePlace, transportPlace), 0.0);
    }
}
