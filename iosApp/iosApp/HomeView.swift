import SwiftUI
import shared

struct HomeView: View {
    let greet = Greeting().greeting()
    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        VStack {
            Text(greet)
            HStack {
                Button(
                    action: viewModel.increase,
                    label: {
                        Text("+")
                    }
                )
                .padding()

                Text("Count: \(viewModel.count)")

                Button(
                    action: viewModel.decrease,
                    label: {
                        Text("-")
                    }
                )
                .padding()

            }
        }
    }
}

final class HomeViewModel: ObservableObject {
    @Published var count: Int = 0

    func increase() {
        count += 1
    }

    func decrease() {
        guard count > 0 else { return }
        count -= 1
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
