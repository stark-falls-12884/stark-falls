import { mainSlice, MainState, userLogin, userLogout } from "../src/mainSlice";

describe("mainSlice test", function () {
  test("login should set username", function () {
    const mainReducer = mainSlice.reducer;
    const initialState = {
      user: null,
      error: null,
    };

    const newState = mainReducer(
      initialState,
      userLogin.fulfilled("loggedInUser", "", { username: "loggedInUser", password: "test" })
    );

    expect(newState.user).toEqual("loggedInUser");
  });

  test("logout should clear username", function () {
    const mainReducer = mainSlice.reducer;
    const initialState = {
      user: "loggedin",
      error: null,
    };

    const newState = mainReducer(initialState, userLogout.fulfilled);

    expect(newState.user).toEqual(null);
  });
});
