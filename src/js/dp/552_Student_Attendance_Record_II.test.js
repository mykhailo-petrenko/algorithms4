import checkRecord from './552_Student_Attendance_Record_II.js';

describe('552. Student Attendance Record II', () => {
  it('should pass Example 1', () => {
    const actual = checkRecord(2);
    expect(actual).toEqual(8);
  });

  it('should pass Example 2', () => {
    const actual = checkRecord(1);
    expect(actual).toEqual(3);
  });

  it('should pass Example 3', () => {
    const actual = checkRecord(10101);
    expect(actual).toEqual(183236316);
  });
});
