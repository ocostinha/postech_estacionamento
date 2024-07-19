db.auth('admin-user', 'admin-password')

db = db.getSiblingDB('estacionamento')

db.createUser({
    user: 'estacionamentoAdmin',
    pwd: '3st4c10n4m3nt0',
    roles: [
        {
            role: 'readWrite',
            db: 'estacionamento',
        },
    ],
})

db.createCollection("estacionamento")
