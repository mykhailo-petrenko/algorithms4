import { argv } from 'node:process';
import { writeFileSync, existsSync } from 'node:fs';

if (argv.length < 3) {
  console.warn(`Please provide link to LeetCode challenge.`);
  console.log(`Usage example: yarn leetcode "473. Matchsticks to Square"`);
  process.exit(1);
}

const title = argv[2];

const FILE_TEMPLATE = `/**
# LeetCode. {TITLE} ()

*/

function placeholder() {

}

export default placeholder;
`;

const TEST_FILE_TEMPLATE = `import testSubject from './{mainFileName}';

describe('{TITLE}', () => {
  function runTest(input, expected) {
    const actual = testSubject(...input);
    expect(actual).toEqual(expected);
  }
  
  (() => {
    const testCases = [
      [
        ["input"], // input
        0 // expected
      ],
    ];

    for (const testCase of testCases) {
      const [ input, expected ] = testCase;

      it(\`should equal \${expected} for "\${input.join()}"\`, () => {
        runTest(input, expected);
      });
    }
  })();
});
`;


(async () => {
  const chunks = title.split(/[.\W]+/iu);

  const fileName = chunks.join('_');

  const path = 'LeetCode/';
  const mainFileName = `${fileName}.js`;
  const testFileName = `${fileName}.test.js`;

  if (
    existsSync(`${path}${mainFileName}`) || existsSync(`${path}${testFileName}`)
  ) {
    console.warn(`${path}${mainFileName} or ${path}${testFileName} already exists.`);
    process.exit(1);
  }

  const mainContent = FILE_TEMPLATE
    .replace('{TITLE}', title)
    .replace('{mainFileName}', mainFileName)
    .replace('{testFileName}', testFileName);

  const testContent = TEST_FILE_TEMPLATE
    .replace('{TITLE}', title)
    .replace('{mainFileName}', mainFileName)
    .replace('{testFileName}', testFileName);

  writeFileSync(`${path}${mainFileName}`, mainContent);
  writeFileSync(`${path}${testFileName}`, testContent);

  console.log(`${path}${mainFileName}, ${path}${testFileName}`);
  console.log('Done.');
})();

