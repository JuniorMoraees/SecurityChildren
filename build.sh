#!/bin/bash

echo "🔧 Iniciando build do projeto com Maven..."

# Executa o Maven e verifica se deu erro
if mvn clean package; then
    echo "✅ Maven build finalizado com sucesso!"
else
    echo "❌ Erro ao compilar com Maven. Abortando build do Docker."
    exit 1
fi

echo "🐳 Construindo imagem Docker..."

# Executa o build da imagem Docker
if docker build -t securitychildren .; then
    echo "✅ Imagem Docker 'securitychildren' construída com sucesso!"
else
    echo "❌ Erro ao construir a imagem Docker."
    exit 1
fi
