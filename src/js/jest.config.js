// https://jestjs.io/docs/configuration

export default {
  clearMocks: true,
  collectCoverage: false,
  coverageDirectory: "coverage",
  moduleFileExtensions: ["js", "jsx", "ts", "tsx", "json", "node",],

  moduleNameMapper: {
    "^~.*\.(jpg|ico|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$": "<rootDir>/__mocks__/fileMock.js",
    "\.(jpg|ico|jpeg|png|gif|eot|otf|webp|svg|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$": "<rootDir>/__mocks__/fileMock.js",
    "^~\/(.*)$": "<rootDir>/src/$1",
    '\.(css|less|scss|sass)$': 'identity-obj-proxy',
  },
  testEnvironment: "node",

  // testMatch: [
  //   "**/__tests__/**/*.[jt]s?(x)",
  //   "**/?(*.)+(spec|test).[tj]s?(x)"
  // ],

  transform: {
    "\\.[jt]sx?$": "babel-jest",
  }
};
