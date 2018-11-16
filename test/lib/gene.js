function *gene(tab) {
  console.log(tab)
  for (const i of tab) {
    yield console.log(i)
  }
}

//const inst = new Gene();

gen = gene([1, 2, 3, 4])
gen.next([1, 2, 3, 4]).value
gen.next([1, 2, 3, 4]).value
gen.next([1, 2, 3, 4]).value
