import SwiftUI
import shared

@main
struct iOSApp: App {
    //let marvelApi = MarvelAPI()
    let mainApi = MainKMM(databaseDriverFactory: DatabaseDriverFactory())
	var body: some Scene {
		WindowGroup {
            //ContentView(viewModel: .init(marvelApi: marvelApi))
            ContentView(viewModel: .init(mainApi: mainApi))
		}
	}
}
