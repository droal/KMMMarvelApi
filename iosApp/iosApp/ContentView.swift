import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View{
        NavigationView{
            listView()
                .navigationTitle("Characters")
                
        }
    }
    
    private func listView() -> AnyView{
        switch viewModel.characters{
        case .loading:
            return AnyView(Text("Loading..").multilineTextAlignment(.center))
            
        case .result(let characters):
            return AnyView(List(characters){charact in ListItemCharacter(character: charact)})
            
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
            
        }
    }
    
}


extension ContentView{
    enum LoadableCharacters{
        case loading
        case result([Character])
        case error(String)
    }
    
    class ViewModel: ObservableObject{
        //let marvelApi: MarvelAPI
        let mainApi: MainKMM
        
        @Published var characters = LoadableCharacters.loading
        
//        init(marvelApi: MarvelAPI){
//            self.marvelApi = marvelApi
//            self.loadCharacters()
//        }
        init(mainApi: MainKMM){
            self.mainApi = mainApi
            self.loadCharacters()
        }
        func loadCharacters(){
            self.characters = .loading
            mainApi.getAllCharacters(updateData: true) { characters, error in
                if let characters = characters{
                    self.characters = .result(characters)
                }else{
                    self.characters = .error(error?.localizedDescription ?? "error")
                }
            }
        }
//        func loadCharacters(){
//            self.characters = .loading
//            marvelApi.getCharacters(completionHandler: {characters, error in
//                if let characters = characters{
//                    self.characters = .result(characters)
//                }else{
//                    self.characters = .error(error?.localizedDescription ?? "error")
//                }
//            })
//        }
    }
}

extension Character: Identifiable { }

//#if DEBUG
//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//	ContentView()
//	}
//}
//#endif
