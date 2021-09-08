import SwiftUI
import shared

struct ListItemCharacter: View{
    var character: Character
    
   
    
    var body: some View{
        HStack(){
            
            VStack(alignment: .leading, spacing: 10.0){
                let description: String = character.description_ ?? ""
                let name: String = character.name
                
                Text(name).bold()
                
                AsyncImage(
                    url: URL(string:"\(character.thumbnail.path).\(character.thumbnail.extension)")!,
                    placeholder: {Text("Loading..")},
                    image: { Image(uiImage: $0).resizable() }
                )
                .frame(idealWidth: UIScreen.main.bounds.width / 8, idealHeight: UIScreen.main.bounds.width / 2, alignment: .center )
                Text(description)
            }
            Spacer()
        }
    }
}


//struct ListItemCharacter_Previews: PreviewProvider {
//    static var previews: some View {
//        ListItemCharacter()
//    }
//}
