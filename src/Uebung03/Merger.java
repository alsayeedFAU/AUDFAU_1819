public interface Merger {
	/**
	 * Appends {@code newLast} to the array {@code old}, e.g. append({0, 1, 2, 3}, 42) gives the new array {0, 1, 2, 3, 42}.
	 * 
	 * @param old
	 *            - the array to which {@code newLast} is to be appended
	 * @param newLast
	 *            - the value to be appended to the array {@code old}
	 * @return a new array containing the values from {@code old} preserving their order as of {@code old} followed by {@code newLast} as last element
	 */
	public long[] append(long[] old, long newLast);

	/**
	 * Prepends {@code newFirst} to the array {@code old}, e.g. prepend(42, {0, 1, 2, 3}) gives the new array {42, 0, 1, 2, 3}.
	 * 
	 * @param newFirst
	 *            - the value to be prepended to the array {@code old}
	 * @param old
	 *            - the array to which {@code newFirst} is to be prepended
	 * @return a new array containing {@code newFirst} as first element at position 0 followed by the values from {@code old} preserving their order as of {@code old}
	 */
	public long[] prepend(long newFirst, long[] old);

	/**
	 * Used to log and check your recursion. YOU MUST CALL THIS METHOD IMMEDIATELY AT THE BEGINNING OF YOUR METHOD.
	 */
	public void merge();
}