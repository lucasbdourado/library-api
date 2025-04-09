package br.com.lucasbdourado.library.dataloader;

import br.com.lucasbdourado.library.dto.city.CityJsonDTO;
import br.com.lucasbdourado.library.dto.neighborhood.NeighborhoodJsonDTO;
import br.com.lucasbdourado.library.dto.state.StateJsonDTO;
import br.com.lucasbdourado.library.entity.city.City;
import br.com.lucasbdourado.library.entity.neighborhood.Neighborhood;
import br.com.lucasbdourado.library.entity.state.State;
import br.com.lucasbdourado.library.repository.StateRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class AddressDataLoader
{

	private final StateRepository stateRepository;

	private final ResourceLoader resourceLoader;

	private static final ObjectMapper mapper = new ObjectMapper();

	public AddressDataLoader(StateRepository stateRepository, ResourceLoader resourceLoader)
	{
		this.stateRepository = stateRepository;
		this.resourceLoader = resourceLoader;
	}

	@PostConstruct
	public void loadAddressData() throws IOException
	{
		if (stateRepository.count() > 0) return;

		Resource stateResource = resourceLoader.getResource("classpath:static/address/states.json");
		Resource cityResource = resourceLoader.getResource("classpath:static/address/cities.json");
		Resource neighborhoodResource = resourceLoader.getResource(
			"classpath:static/address/neighborhoods.json");

		List<StateJsonDTO> stateDTOList = loadStateDTOS(stateResource);

		List<CityJsonDTO> cityDTOList = loadCityDTOS(cityResource);

		List<NeighborhoodJsonDTO> neighborhoodDTOList = loadNeighborhoodDTOS(neighborhoodResource);

		Map<String, State> stateMap = new HashMap<>();
		Map<String, City> cityMap = new HashMap<>();

		for (StateJsonDTO stateDTO : stateDTOList)
		{
			State state = new State();
			state.setName(stateDTO.name());
			state.setCode(stateDTO.code());
			state.setAcronym(stateDTO.acronym());
			stateMap.put(stateDTO.acronym(), state);
		}

		for (CityJsonDTO cityDTO : cityDTOList)
		{
			City city = new City();
			city.setName(cityDTO.name());
			city.setCode(cityDTO.code());
			State state = stateMap.get(cityDTO.stateAcronym());
			city.setState(state);
			state.getCities().add(city);

			cityMap.put(cityDTO.name() + "|" + cityDTO.stateAcronym(), city);
		}

		for (NeighborhoodJsonDTO neighborhoodDTO : neighborhoodDTOList)
		{
			Neighborhood neighborhood = new Neighborhood();
			neighborhood.setCode(neighborhoodDTO.code());
			neighborhood.setName(neighborhoodDTO.name());
			neighborhood.setStateAcronym(neighborhoodDTO.stateAcronym());
			City city = cityMap.getOrDefault(
				neighborhoodDTO.name().split(" - ")[1] + "|" + neighborhoodDTO.stateAcronym(), null);

			if (city != null)
			{
				neighborhood.setCity(city);
				city.getNeighborhoodList().add(neighborhood);
			}
		}

		stateRepository.saveAll(stateMap.values());
	}

	private static List<StateJsonDTO> loadStateDTOS(Resource stateFile)
	{
		try (InputStream stateStream = stateFile.getInputStream())
		{
			Map<String, Object> result = mapper.readValue(stateStream, new TypeReference<>() {});

			return mapper.convertValue(result.get("data"), new TypeReference<>()
			{
			});
		}
		catch (IOException e)
		{
			throw new UncheckedIOException("Erro ao ler arquivo de estados", e);
		}
	}

	private static List<CityJsonDTO> loadCityDTOS(Resource cityFile)
	{
		try (InputStream cityStream = cityFile.getInputStream())
		{
			Map<String, Object> result = mapper.readValue(cityStream, new TypeReference<>() {});

			return mapper.convertValue(result.get("data"), new TypeReference<>()
			{
			});
		}
		catch (IOException e)
		{
			throw new UncheckedIOException("Erro ao ler arquivo de cidades", e);
		}
	}

	private static List<NeighborhoodJsonDTO> loadNeighborhoodDTOS(Resource neighborhoodFile) throws IOException
	{
		try (InputStream neighborhoodStream = neighborhoodFile.getInputStream())
		{
			Map<String, Object> result = mapper.readValue(neighborhoodStream, new TypeReference<>() {});

			return mapper.convertValue(result.get("data"), new TypeReference<>()
			{
			});
		}
		catch (IOException e)
		{
			throw new UncheckedIOException("Erro ao ler arquivo de bairros", e);
		}
	}
}
