$('#getUserMarket').on('click', () => {
    const baseUrl = 'http://localhost:8088/';
    const apiUrl = baseUrl + 'userMarket/all'

    $.ajax({
        type: "get",
        url: apiUrl,
        dataType: "text",
    }).done((data) => {
        var userMarket = JSON.parse(data)
        console.log(userMarket)

        const userListDOM = document.getElementById('userList')     
        userMarket.map((market) => {
            var tr = document.createElement('tr')
            var idDOM = document.createElement('td')
            var nameDOM = document.createElement('td')
            var emailDOM = document.createElement('td') 
            var latDOM = document.createElement('td')
            var lngDOM = document.createElement('td')

            idDOM.append(market.id)
            nameDOM.append(market.name)
            emailDOM.append(market.address)
            latDOM.append(market.lat)
            lngDOM.append(market.lng)

            tr.appendChild(idDOM)
            tr.appendChild(nameDOM)
            tr.appendChild(emailDOM)
            tr.appendChild(latDOM)
            tr.appendChild(lngDOM)
            userListDOM.appendChild(tr)
        })
    });
})