let alicePrivateKey, bobPrivateKey;
let alicePublicKey, bobPublicKey;
let sharedSecretKey;

function generateKeys() {
    alicePrivateKey = parseInt(document.getElementById('alicePrivateKey').value);
    bobPrivateKey = parseInt(document.getElementById('bobPrivateKey').value);

    if (isNaN(alicePrivateKey) || isNaN(bobPrivateKey)) {
        alert("Please enter valid private keys.");
        return;
    }

    alicePublicKey = Math.pow(2, alicePrivateKey) % 1000;
    bobPublicKey = Math.pow(2, bobPrivateKey) % 1000;

    sharedSecretKey = Math.pow(bobPublicKey, alicePrivateKey) % 1000;

    document.getElementById('alicePublicKey').innerText = alicePublicKey;
    document.getElementById('bobPublicKey').innerText = bobPublicKey;

    console.log("Alice's private key:", alicePrivateKey);
    console.log("Alice's public key:", alicePublicKey);
    console.log("Bob's private key:", bobPrivateKey);
    console.log("Bob's public key:", bobPublicKey);
    console.log("Shared secret key:", sharedSecretKey);
}

function encryptMessage(message) {
    let encryptedMessage = '';
    for (let i = 0; i < message.length; i++) {
        let charCode = message.charCodeAt(i);
        encryptedMessage += String.fromCharCode((charCode + sharedSecretKey) % 256);
    }
    return encryptedMessage;
}

function decryptMessage(encryptedMessage) {
    let decryptedMessage = '';
    for (let i = 0; i < encryptedMessage.length; i++) {
        let charCode = encryptedMessage.charCodeAt(i);
        decryptedMessage += String.fromCharCode((charCode - sharedSecretKey + 256) % 256);
    }
    return decryptedMessage;
}

function sendMessage(sender) {
    generateKeys();

    let message, chat;
    if (sender === 'alice') {
        message = document.getElementById('aliceMessage').value;
        chat = document.getElementById('aliceChat');
    } else {
        message = document.getElementById('bobMessage').value;
        chat = document.getElementById('bobChat');
    }

    if (message.trim() !== '') {
        let newMessage = document.createElement('div');
        newMessage.innerText = 'Sent: ' + message;
        newMessage.classList.add('message');
        chat.appendChild(newMessage);

        let encryptedMessage = encryptMessage(message);
        let encryptedChat = document.getElementById(sender === 'alice' ? 'bobChat' : 'aliceChat');
        let newEncryptedMessage = document.createElement('div');
        newEncryptedMessage.innerText = 'Encrypted: ' + encryptedMessage;
        newEncryptedMessage.classList.add('message');
        encryptedChat.appendChild(newEncryptedMessage);

        if (sender === 'alice') {
            setTimeout(function() {
                let bobResponse = document.createElement('div');
                bobResponse.innerText = 'Received: ' + decryptMessage(encryptedMessage);
                bobResponse.classList.add('message');
                document.getElementById('bobChat').appendChild(bobResponse);
            }, 1000);
        } else {
            setTimeout(function() {
                let aliceResponse = document.createElement('div');
                aliceResponse.innerText = 'Received: ' + decryptMessage(encryptedMessage);
                aliceResponse.classList.add('message');
                document.getElementById('aliceChat').appendChild(aliceResponse);
            }, 1000);
        }

        if (sender === 'alice') {
            document.getElementById('aliceMessage').value = '';
        } else {
            document.getElementById('bobMessage').value = '';
        }
    }
}
