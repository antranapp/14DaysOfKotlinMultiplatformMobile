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
    private let _homeViewModel = shared.CounterViewModel()

    @Published var count: Int = 0

    init() {
        _homeViewModel.count.addObserver { [weak self] value in
            guard let value = value else { return }
            self?.count = Int(truncating: value)
        }
    }

    func increase() {
        _homeViewModel.increase()
    }

    func decrease() {
        _homeViewModel.decrease()
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
