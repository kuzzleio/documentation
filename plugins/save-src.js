module.exports = options => {

    return (files, metalsmith, done) => {
        setImmediate(done)

        for (let file in files) {
            files[file].src = file
        }
    }
}
