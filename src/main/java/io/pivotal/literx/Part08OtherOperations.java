package io.pivotal.literx;

import java.util.Optional;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	// TODO Create a Flux of user from Flux of username, firstname and lastname.
	Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux,
			Flux<String> lastnameFlux) {
		return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux).flatMap(t -> Flux.just(new User(t.getT1(), t.getT2(), t.getT3()))).log();
	}

//========================================================================================

	// TODO Return the mono which returns its value faster
	Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
		return Mono.first(mono1,mono2);
	}

//========================================================================================

	// TODO Return the flux which returns the first value faster
	Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
		return Flux.first(flux1, flux2);
	}

//========================================================================================

	// TODO Convert the input Flux<User> to a Mono<Void> that represents the
	// complete signal of the flux
	Mono<Void> fluxCompletion(Flux<User> flux) {
		return flux.then();
	}

//========================================================================================

	// TODO Return a valid Mono of user for null input and non null input user
	// (hint: Reactive Streams do not accept null values)
	Mono<User> nullAwareUserToMono(User user) {
		return Mono.justOrEmpty(user);
	}

//========================================================================================

	// TODO Return the same mono passed as input parameter, expect that it will emit
	// User.SKYLER when empty
	Mono<User> emptyToSkyler(Mono<User> mono) {
		//非静态方法 而是对象方法 因为只有在对象上消费数据且没有才能使用另外一个默认值
		return mono.defaultIfEmpty(User.SKYLER);
	}

}
