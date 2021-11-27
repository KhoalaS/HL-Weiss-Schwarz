class GalleryItem extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});

        const wrapper = document.createElement('div')
        const a = document.createElement('a')
        const img = document.createElement('img')

        img.setAttribute('src', '')
        img.setAttribute('onclick', 'expandImg')

        a.appendChild(img)
        wrapper.appendChild(a)

        this.shadowRoot.append(wrapper);

    }

}

class FlipImage extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});

        const wrapper = document.createElement('div')
        const card = document.createElement('div')
        const front = document.createElement('div')
        const back = document.createElement('div')
        const img_f = document.createElement('img')
        const img_b = document.createElement('img')
        img_f.style.borderRadius = '10px'
        img_b.style.borderRadius = '10px'

        wrapper.setAttribute('class', 'flip_container')
        wrapper.setAttribute('part', 'flip_container')
        wrapper.setAttribute('onClick', 'flip(this)')
        card.setAttribute('class', 'flip_card')
        card.setAttribute('part', 'flip_card')

        img_f.setAttribute('src', 'images/placeholder_card.png')
        img_b.setAttribute('src', '')
        img_f.setAttribute('width', '100%')
        img_b.setAttribute('width', '100%')
        front.setAttribute('class', 'frontImage')
        back.setAttribute('class', 'backImage')
        front.setAttribute('part', 'frontImage')
        back.setAttribute('part', 'backImage')
        front.appendChild(img_f)
        back.appendChild(img_b)

        wrapper.appendChild(front)
        wrapper.appendChild(back)

        card.appendChild(wrapper)

        this.shadowRoot.append(card);

    }
}

customElements.define('flip-image', FlipImage);
customElements.define('gallery-item', GalleryItem);

function getInventory(){
    fetch('/user/getInv')
        .then(function(response){
            return response.json()
        })
        .then(function(data){
            const parent = document.getElementById('invBox')
            if(parent.children.length != 0){
                while(parent.firstChild){
                    parent.removeChild(parent.firstChild)
                }
            }
            data.forEach(populateInv)
        })
        .catch(function(err){
            console.log(err)
        })

}

function populateInv(value){
    let item = document.createElement('gallery-item')
    item.setAttribute('class', 'inv')
    item.setAttribute('data-all',JSON.stringify(value))

    const shadow = item.shadowRoot;
    const img = shadow.querySelector('img')
    img.setAttribute('src', 'images/'+value.png)
    img.setAttribute('width', '100%')
    img.style.borderRadius = '10px'

    let invBox = document.getElementById('invBox')
    invBox.appendChild(item)
}


//TODO: PopUp Class needed for Readability
function booster(){
    let main = document.getElementById('main')
    let p = document.createElement('div');
    p.setAttribute('class', 'popUp')

    let bar = document.createElement('div')
    let title = document.createElement('span')
    let close = document.createElement('button')
    let cross = document.createElement('img')
    let overlay = document.createElement('div')

    overlay.setAttribute('class', 'overlay')
    overlay.setAttribute('id', 'overlay')
    close.setAttribute('class', 'uiButton')
    cross.setAttribute('class', 'icon')
    cross.setAttribute('src', 'images/cross.svg')
    title.setAttribute('class', 'title')
    close.appendChild(cross)
    close.addEventListener('click', function(){
        main.removeChild(p)
        main.removeChild(overlay)
        getInventory()
     })
    title.textContent = 'Result'
    bar.appendChild(title)
    bar.appendChild(close)
    bar.setAttribute('class', 'bar')
    p.appendChild(bar)
    main.appendChild(overlay)

    for(let i = 0; i < 8; i++){
        const item = document.createElement('flip-image')
        item.setAttribute('class', 'gallery')
        item.setAttribute('id', 'card'+i)
        p.appendChild(item)
    }

   main.appendChild(p)

    fetch('/booster')
        .then(function(response){
            return response.json()
        })
        .then(function(data){
            data.forEach(populatePopUp)
        })
        .catch(function(err){
            console.log(err)
        })
}

function populatePopUp(value, index){
    const card = document.getElementById('card'+index)
    const shadow = card.shadowRoot
    const flipcard = shadow.querySelector('.flip_card')
    const container = flipcard.querySelector('.flip_container')
    const div = container.querySelector('.backImage')
    const img_b = div.querySelector('img')
    img_b.setAttribute('src', 'images/'+ value.png)
}

function swap(e){
    var uImg = e.getAttribute('iSwap')
    e.setAttribute('src', uImg)
}

function flip(e){
    e.setAttribute('part', 'flip_container flip_container_active')
}

